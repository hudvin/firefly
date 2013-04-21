package com.nevilon.firefly

import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.action.search.{SearchType}
import org.elasticsearch.index.query.QueryBuilders
import java.util.Date
import org.elasticsearch.common.Base64
import org.elasticsearch.common.settings.ImmutableSettings
import org.elasticsearch.action.get.GetResponse

/**
 * Created with IntelliJ IDEA.
 * User: hudvin
 * Date: 4/6/13
 * Time: 5:35 PM
 */

object Runner {

  def main(args: Array[String]) {
    val esClient = new ESClient

  }


}

class ESClient {

  import org.elasticsearch.node.NodeBuilder._

  val client = new TransportClient(ImmutableSettings.settingsBuilder().put("cluster.name", "es").build());
  client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300))


  import org.elasticsearch.common.xcontent.XContentFactory._;

  client.prepareIndex("indexname", "type1", "1")
    .setSource(jsonBuilder.startObject
    .field("index", "binary index")
    .field("binary", Base64.decode("shimi"))
    .endObject
  )
    .execute.actionGet

  val settings =  ImmutableSettings.settingsBuilder() //
    .loadFromClasspath("elasticsearch.json")


  val res =  client.admin().indices().prepareCreate("papersfffff")
    .setSettings(settings).execute().actionGet()


  val response = client.prepareIndex("twitter", "tweet", "1")
    .setSource(jsonBuilder()
    .startObject()
    .field("user", "kimchy")
    .field("postDate", new Date())
    .field("message", "trying out Elastic Search")
    .endObject()
  )
    .execute()
    .actionGet();

  Console println response.getIndex


  val searchResponse = client.prepareSearch()
    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
    .addHighlightedField("message", 0, 0)
    .setQuery(QueryBuilders.matchQuery("message", "Search"))
    .execute().actionGet()

  val it =  searchResponse.hits().iterator()
  while(it.hasNext){
    val n  = it.next()
     Console println n.getHighlightFields
  }
  println(searchResponse)


}
