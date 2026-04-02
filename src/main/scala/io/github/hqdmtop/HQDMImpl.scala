package io.github.hqdmtop

case class SpatioTemporalExtentImpl(
    id: Identifier,
    temporalPartOf: Option[Set[SpatioTemporalExtent]],
    geometry: Option[Geometry],
    beginning: Event,
    ending: Event
) extends SpatioTemporalExtent

case class EventImpl(id: Identifier, geometry: Option[Geometry]) extends Event


