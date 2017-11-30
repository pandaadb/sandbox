//package elastic;
//
//import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
//import static org.elasticsearch.index.query.QueryBuilders.*;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//
//import org.elasticsearch.action.bulk.BulkProcessor;
//import org.elasticsearch.action.bulk.BulkProcessor.Listener;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.search.SearchHit;
//
//public class EsScroll {
//
//	public static void main(String[] args) {
//		Settings settings = Settings.builder().put("cluster.name", "es-events-dev").build();
//		TransportClient client = TransportClient.builder().settings(settings).build();
//		client.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("localhost", 9300)));
//		
//		QueryBuilder qb = matchPhrasePrefixQuery("eventType", "VAST");
//		
//		BulkProcessor processor = BulkProcessor.builder(client, new Listener() {
//			
//			@Override
//			public void beforeBulk(long executionId, BulkRequest request) {
//				System.out.println("Creating bulk, actions: " + request.numberOfActions());
//			}
//			
//			@Override
//			public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
//				System.err.println("Bulk failed");
//			}
//			
//			@Override
//			public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
//				System.out.println("Bulk success: " + request.numberOfActions() );
//			}
//		}).setBulkActions(1000).build();
//		
//		SearchResponse scrollResp = client.prepareSearch("events-eurostar-*")
//		        .setScroll(new TimeValue(60000))
//		        .setQuery(qb)
//		        .setSize(1000).get();
//		
//		//Scroll until no hits are returned
//		do {
//		    for (SearchHit hit : scrollResp.getHits().getHits()) {
//		    	
//		    	System.out.println(hit.getSourceAsString());
//		    	
////		    	UpdateRequest updateRequest = new UpdateRequest();
////		    	updateRequest.index(hit.getIndex());
////		    	updateRequest.type(hit.getType());
////		    	updateRequest.id(hit.getId());
////		    	try {
////					XContentBuilder string = jsonBuilder().startObject()
////							.field("test1").value("Artur Update")
////							.endObject();
////					updateRequest.doc(string);
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////		    	processor.add(updateRequest);
//		    	System.out.println("Update: " + hit.getId());
//		    }
//
//		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
//		} while(scrollResp.getHits().getHits().length != 0);
//	}
//}
