package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.CallTenderMail;
import com.axelor.db.JpaRepository;

public class CallTenderMailRepository extends JpaRepository<CallTenderMail> {

  public CallTenderMailRepository() {
    super(CallTenderMail.class);
  }
}
