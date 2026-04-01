package io.github.hqdmtop

import org.scalatest.funsuite.AnyFunSuite

import java.util.UUID

class HQDMTestSuite extends AnyFunSuite:

  class ThingWithTemporalParts(
      val id: Identifier,
      val temporalPartOf: Option[Set[SpatioTemporalExtent]],
      val beginning: Event,
      val ending: Event,
      val geometry: Option[Geometry]
  ) extends SpatioTemporalExtent

  test(
    "can create a SpatioTemporalExtent with temporal parts of the correct type"
  ):

    val ste1Start = new Event:
      val id = UUID.randomUUID()
      val geometry: Option[Geometry] = None

    val ste1End = new Event:
      val id = UUID.randomUUID()
      val geometry: Option[Geometry] = None

    val ste1 = new ThingWithTemporalParts(
      id = UUID.randomUUID(),
      temporalPartOf = None,
      beginning = ste1Start,
      ending = ste1End,
      geometry = None
    )

    val ste2Start = new Event {
      val id = UUID.randomUUID()
      val geometry: Option[Geometry] = None
    }
    val ste2End = new Event {
      val id = UUID.randomUUID()
      val geometry: Option[Geometry] = None
    }
    val ste2 = new ThingWithTemporalParts(
      id = UUID.randomUUID(),
      temporalPartOf = Some(Set(ste1)),
      beginning = ste2Start,
      ending = ste2End,
      geometry = None
    )
