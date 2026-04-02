package io.github.hqdmtop

import cats.{Id, ~>}

import java.util.UUID
import scala.collection.mutable

def hqdmStoreCompiler: HQDMStoreA ~> Id = new (HQDMStoreA ~> Id) {

  val store = mutable.Map.empty[UUID, Thing]

  override def apply[A](fa: HQDMStoreA[A]): Id[A] =
    fa match
      case CreateNewEvent() =>
        val id = UUID.randomUUID()
        val e = EventImpl(id, None)
        store += (id -> e)
        e
      case CreateNewSpatioTemporalExtent(
            beginning,
            ending,
            geometry,
            temporalPartOf
          ) =>
        val id = UUID.randomUUID()
        val ste = SpatioTemporalExtentImpl(
          id,
          temporalPartOf,
          geometry,
          beginning,
          ending
        )
        store += (id -> ste)
        ste
      case InsertThing(t: Thing) =>
        store += (t.id -> t)
        ()
      case GetThingById(id: Identifier) =>
        store(id).asInstanceOf[A]
      case GetStore() => store.asInstanceOf[A]

}

