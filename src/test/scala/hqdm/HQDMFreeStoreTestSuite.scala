package io.github.hqdmtop

import org.scalatest.funsuite.AnyFunSuite

class HQDMFreeStoreTestSuite extends AnyFunSuite:

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

    val ss = for
      ste1Start <- createNewEvent()
      ste1End <- createNewEvent()
      ste1 <- createNewSpatioTemporalExtent(ste1Start, ste1End, None, None)

      ste2Start <- createNewEvent()
      ste2End <- createNewEvent()
      ste2 <- createNewSpatioTemporalExtent(ste2Start, ste2End, None, Some(Set(ste1)))
    yield (ste1, ste2)
