package elastic;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;

public class EsReportingFixer {

	
	
	public static void main(String[] args) {
		
		Map<String,String> titles = new HashMap<>();
		titles.put("7940648", "Soccer AM");
		titles.put("7588667", "Soccer AM");
		titles.put("1137240", "Soccer AM");
		titles.put("3847299", "Sky Sports News");
		
		Settings settings = Settings.builder().put("cluster.name", "es-events").build();
		TransportClient client = TransportClient.builder().settings(settings).build();
		client.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("localhost", 9302)));
		
		QueryBuilder qb = termsQuery("contentId","7940648");
		
		BulkProcessor processor = BulkProcessor.builder(client, new Listener() {
			
			@Override
			public void beforeBulk(long executionId, BulkRequest request) {
				System.out.println("Creating bulk, actions: " + request.numberOfActions());
			}
			
			@Override
			public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
				System.err.println("Bulk failed");
			}
			
			@Override
			public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
				System.out.println("Bulk success: " + request.numberOfActions() );
				response.forEach(r -> {
					System.out.println("Update id: " + r.getId() +  " Failure: " + r.getFailureMessage() + " response: " + r.getResponse());
				});
			}
		}).setBulkActions(100).build();
		
		SearchResponse scrollResp = client.prepareSearch("events-national_express-*")
		        .setScroll(new TimeValue(60000))
		        .setQuery(qb)
		        .addSort(SortBuilders.fieldSort("timestamp"))
		        .setSize(1000).get();
		
		int count = 0;
		
		//Scroll until no hits are returned
		do {
		    for (SearchHit hit : scrollResp.getHits().getHits()) {
//		    	System.out.println(hit.getSourceAsString());
		    	count ++;
		    	String contentId = hit.getSource().get("contentId").toString();
		    	Object object = hit.getSource().get("movieTitle");
		    	if(object == null || !object.toString().equals("No mapping found")) {
		    		String t = object == null ? "NULL" : object.toString();
		    		System.out.println("Id " + hit.getId() + " has not made the cut. Title: " + t );
		    		continue;
		    	} else {
		    		if(object.toString().equals(titles.get(contentId))) {
		    			System.out.println("Already the correct title");
		    			continue;
		    		}
		    	}
		    	
		    	String newTitle = titles.get(contentId);
		    	if(newTitle == null ) {
		    		System.err.println("Movie title somehow now mapped for id: " + hit.getId());
		    		continue;
		    	}
		    	
		    	UpdateRequest updateRequest = new UpdateRequest();
		    	updateRequest.index(hit.getIndex());
		    	updateRequest.type(hit.getType());
		    	updateRequest.id(hit.getId());
		    	try {
					XContentBuilder string = jsonBuilder().startObject()
							.field("movieTitle").value(newTitle)
							.endObject();
					updateRequest.doc(string);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	processor.add(updateRequest);
		    	System.out.println("Update: " + hit.getId());
		    }

		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
		} while(scrollResp.getHits().getHits().length != 0);
		
		processor.flush();
		
		System.out.println("Done. Count: " + count);
	}
}
