package io.github.hqdmtop

import cats.free.Free
import cats.free.Free.liftF
import scala.collection.mutable

// A for Algebra
sealed trait HQDMStoreA[A]

case class CreateNewEvent() extends HQDMStoreA[Event]

case class CreateNewSpatioTemporalExtent(
    beginning: Event,
    ending: Event,
    geometry: Option[Geometry],
    temporalPartOf: Option[Set[SpatioTemporalExtent]]
) extends HQDMStoreA[SpatioTemporalExtent]

case class InsertThing[A <: Thing](thing: A) extends HQDMStoreA[Unit]

case class GetThingById(id: Identifier) extends HQDMStoreA[Option[Thing]]

case class GetStore[A <: Thing]() extends HQDMStoreA[mutable.Map[Identifier, A]]

// HQDMStore Free monad
type HQDMStore[A] = Free[HQDMStoreA, A]

// Smart Constructors
def createNewEvent(): HQDMStore[Event] =
  liftF[HQDMStoreA, Event](CreateNewEvent())

def createNewSpatioTemporalExtent(
    beginning: Event,
    ending: Event,
    geometry: Option[Geometry],
    temporalPartOf: Option[Set[SpatioTemporalExtent]]
): HQDMStore[SpatioTemporalExtent] =
  liftF[HQDMStoreA, SpatioTemporalExtent](
    CreateNewSpatioTemporalExtent(beginning, ending, geometry, temporalPartOf)
  )

def insertThing[A <: Thing](thing: A): HQDMStore[Unit] =
  liftF[HQDMStoreA, Unit](InsertThing(thing))

def getThingById(id: Identifier): HQDMStore[Option[Thing]] =
  liftF[HQDMStoreA, Option[Thing]](GetThingById(id))

def getStore[A <: Thing]: HQDMStore[mutable.Map[Identifier, A]] =
  liftF[HQDMStoreA, mutable.Map[Identifier, A]](GetStore())

