package io.github.hqdmtop

import cats.{Id, ~>}

import java.util.UUID
import scala.collection.mutable

case class SpatioTemporalExtentImpl(id: Identifier, temporalPartOf: Option[Set[SpatioTemporalExtent]], geometry: Option[Geometry], beginning: Event, ending: Event) extends SpatioTemporalExtent
case class EventImpl(id:Identifier, geometry: Option[Geometry]) extends Event

def hqdmStoreCompiler: HQDMStoreA ~> Id = new(HQDMStoreA ~> Id) {

  val store = mutable.Map.empty[UUID, Thing]

  override def apply[A](fa: HQDMStoreA[A]): Id[A] =
    fa match
      case CreateNewEvent() =>
        val id = UUID.randomUUID()
        val e = EventImpl(id, None)
        store += (id -> e)
        e
      case CreateNewSpatioTemporalExtent(beginning: Event, ending: Event, geometry: Option[Geometry], temporalPartOf: Option[Set[SpatioTemporalExtent]]) =>
        val id = UUID.randomUUID()
        val ste = SpatioTemporalExtentImpl(id, temporalPartOf, geometry, beginning, ending)
        store += (id -> ste)
        ste
      case InsertThing(t: Thing) =>
        store += (t.id -> t)
        ()
      case GetThingById(id: Identifier) =>
        store(id).asInstanceOf[A]
      case GetStore() => store.asInstanceOf[A]

}