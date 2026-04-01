package io.github.hqdmtop

import java.util.UUID

type Identifier = UUID

trait Thing:
  val id: Identifier

trait Class[A <: Thing] extends Thing:
  val members: Set[A]

trait Aggregation[A <: Thing] extends Thing:
  val parts: Set[A]

trait Event extends SpatialExtent

trait TemporalExtent extends Thing:
  val beginning: Event
  val ending: Event

trait Geometry 

trait SpatialExtent extends Thing:
  val geometry: Option[Geometry]

trait SpatioTemporalExtent extends TemporalExtent, SpatialExtent:
  val temporalPartOf: Option[Set[SpatioTemporalExtent]]
