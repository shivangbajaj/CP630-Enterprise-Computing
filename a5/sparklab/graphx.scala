//GraphX 

import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

val myVertices = sc.makeRDD(Array(
(1L, "Ann"),
(2L, "Bill"),
(3L, "Charles"),
(4L, "Diane"),
(5L, "Went to gym this morning")
))

val myEdges = sc.makeRDD(Array(
Edge(1L, 2L, "is-friends-with"),
Edge(2L, 3L, "is-friends-with"),
Edge(3L, 4L, "is-friends-with"),
Edge(4L, 5L, "Likes-status"),
Edge(3L, 5L, "Wrote-status")
))

val myGraph = Graph(myVertices, myEdges)
myGraph.vertices.collect
myGraph.edges.collect

//triplets() method to join together the vertices and edges based on VertexId.
myGraph.triplets.collect

//map/reduce
myGraph.mapTriplets(t => (t.attr, t.attr=="is-friends-with" && t.srcAttr.toLowerCase.contains("a"))).triplets.collect

//sendToSrc—Sends a message of type Msg to the source vertex.
//sendToDst—Sends a message of type Msg to the destination vertex.
//indegress
myGraph.aggregateMessages[Int](_.sendToDst(1), _ + _).collect


//outdegree
myGraph.aggregateMessages[Int](_.sendToSrc(1), _ + _).collect
myGraph.aggregateMessages[Int](_.sendToSrc(1),_ + _).join(myGraph.vertices).collect


//use the swap() method to swap Tuple2
myGraph.aggregateMessages[Int](_.sendToSrc(1),_ + _).join(myGraph.vertices).map(_._2.swap).collect

//rightOuterJoin() instead of join() to pull in “forgotten” vertices
myGraph.aggregateMessages[Int](_.sendToSrc(1),_ + _).rightOuterJoin(myGraph.vertices).map(_._2.swap).collect
myGraph.aggregateMessages[Int](_.sendToSrc(1),_ + _).rightOuterJoin(myGraph.vertices).map(x => (x._2._2, x._2._1.getOrElse(0))).collect

//Iterated (via recursion) Map/Reduce to find distance of furthest vertex
def sendMsg(ec: EdgeContext[Int,String,Int]): Unit = {
ec.sendToDst(ec.srcAttr+1)
}

def mergeMsg(a: Int, b: Int): Int = {
math.max(a,b)
}

def propagateEdgeCount(g:Graph[Int,String]):Graph[Int,String]={
val verts = g.aggregateMessages[Int](sendMsg, mergeMsg)
val g2 = Graph(verts,g.edges)
val check = g2.vertices.join(g.vertices).map(x=>x._2._1-x._2._2).reduce(_+_)
if (check > 0)
propagateEdgeCount(g2)
else
g
}

val initialGraph = myGraph.mapVertices((_,_) => 0)
propagateEdgeCount(initialGraph).vertices.collect

//InvokingShortestPath
lib.ShortestPaths.run(myGraph,Array(3)).vertices.collect

//page ranking
import org.apache.spark.graphx._
val graph = GraphLoader.edgeListFile(sc, "data/cit-HepTh.txt")
graph.inDegrees.reduce((a,b) => if (a._2 > b._2) a else b)
graph.vertices.take(10)

// may take a few seconds to return
val v = graph.pageRank(0.001).vertices
v.take(10)
v.reduce((a,b) => if (a._2 > b._2) a else b)

//PersonalizedPageRank
graph.personalizedPageRank(9207016, 0.001).vertices.filter(_._1 != 9207016).reduce((a,b) => if (a._2 > b._2) a else b)



