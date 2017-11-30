//package elastic;
//
//import static org.elasticsearch.index.query.QueryBuilders.matchPhrasePrefixQuery;
//
//import java.net.InetSocketAddress;
//
//
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.search.SearchHit;
//
//public class EsScrollChart {
//
//	public static void main(String[] args) {
//		Settings settings = Settings.builder().put("cluster.name", "es-events-dev").build();
//		TransportClient client = TransportClient.builder().settings(settings).build();
//		client.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("localhost", 9300)));
//		
//		QueryBuilder qb = matchPhrasePrefixQuery("eventType", "VAST");
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
//		    	System.out.println("Update: " + hit.getId());
//		    }
//
//		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
//		} while(scrollResp.getHits().getHits().length != 0);
//		
//		
//		
////		 Scene scene = new Scene(new Group());
////	        stage.setTitle("Imported Fruits");
////	        stage.setWidth(500);
////	        stage.setHeight(500);
////	 
////	        ObservableList<PieChart.Data> pieChartData =
////	                FXCollections.observableArrayList(
////	                new PieChart.Data("Grapefruit", 13),
////	                new PieChart.Data("Oranges", 25),
////	                new PieChart.Data("Plums", 10),
////	                new PieChart.Data("Pears", 22),
////	                new PieChart.Data("Apples", 30));
////	        final PieChart chart = new PieChart(pieChartData);
////	        chart.setTitle("Imported Fruits");
////
////	        ((Group) scene.getRoot()).getChildren().add(chart);
////	        stage.setScene(scene);
////	        stage.show();
//		
//	}
//}
