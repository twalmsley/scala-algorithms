package digraph

/** A Vertex/Edge digraph.
  */
case class Vertex[A](id: String, value: A)
case class Edge[A](
    id: String,
    source: Vertex[A],
    target: Vertex[A]
)
case class ReifiedEdge[A](
    id: String,
    source: Edge[A],
    target: Vertex[A]
)
case class MetaEdge[A](
    id: String,
    source: Edge[A],
    target: Edge[A]
)
case class ReifiedMetaEdge[A](
    id: String,
    source: MetaEdge[A],
    target: Vertex[A]
)
case class Digraph[A](
    vertexes: Set[Vertex[A]],
    edges: Set[Edge[A]],
    reifiedEdges: Set[ReifiedEdge[A]],
    reifiedMetaEdges: Set[ReifiedMetaEdge[A]],
    metaEdges: Set[MetaEdge[A]]
):
  def add(v: Vertex[A]): Digraph[A] = this.copy(vertexes = this.vertexes + v)
  def add(e: Edge[A]): Digraph[A] =
    this.copy(this.vertexes + e.source + e.target, edges = edges + e)
  def add(e: ReifiedEdge[A]): Digraph[A] =
    this.copy(
      vertexes = vertexes + e.target,
      edges = edges + e.source,
      reifiedEdges = reifiedEdges + e
    )
  def add(e: MetaEdge[A]): Digraph[A] =
    this.copy(edges = edges + e.source + e.target, metaEdges = metaEdges + e)
  def add(e: ReifiedMetaEdge[A]): Digraph[A] = this.copy(
    vertexes = vertexes + e.target,
    metaEdges = metaEdges + e.source,
    reifiedMetaEdges = reifiedMetaEdges + e
  )

case object Digraph:
  def apply[A]():Digraph[A] = Digraph(Set(), Set(), Set(), Set(), Set())
