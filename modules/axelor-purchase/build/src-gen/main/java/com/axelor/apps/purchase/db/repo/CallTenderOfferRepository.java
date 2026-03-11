package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.CallTenderOffer;
import com.axelor.db.JpaRepository;

public class CallTenderOfferRepository extends JpaRepository<CallTenderOffer> {

  public CallTenderOfferRepository() {
    super(CallTenderOffer.class);
  }

  public static final int STATUS_DRAFT = 1;
  public static final int STATUS_SENT = 2 ;
  public static final int STATUS_REPLIED = 3;
  public static final int STATUS_SELECTED = 4;
  public static final int STATUS_REJECTED = 5;
}
