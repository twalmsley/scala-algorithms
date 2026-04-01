package io.github.hqdmtop

import org.scalatest.funsuite.AnyFunSuite

class HQDMFreeStoreTestSuite extends AnyFunSuite:

  test(
    "can create a SpatioTemporalExtent with temporal parts of the correct type"
  ):

    val program = for
      ste1Start <- createNewEvent()
      ste1End <- createNewEvent()
      ste1 <- createNewSpatioTemporalExtent(ste1Start, ste1End, None, None)

      ste2Start <- createNewEvent()
      ste2End <- createNewEvent()
      ste2 <- createNewSpatioTemporalExtent(
        ste2Start,
        ste2End,
        None,
        Some(Set(ste1))
      )
      store <- getStore
    yield store

    val result = program.foldMap(hqdmStoreCompiler)

    assert(result.size == 6)
    result.foreachEntry((id, t) => assert(id == t.id))
