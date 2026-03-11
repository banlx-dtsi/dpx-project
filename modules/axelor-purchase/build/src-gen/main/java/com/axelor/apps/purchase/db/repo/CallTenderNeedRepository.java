package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.CallTenderNeed;
import com.axelor.db.JpaRepository;

public class CallTenderNeedRepository extends JpaRepository<CallTenderNeed> {

  public CallTenderNeedRepository() {
    super(CallTenderNeed.class);
  }

  // TYPE SELECT
  public static final int MANUAL_TYPE = 1;
}
