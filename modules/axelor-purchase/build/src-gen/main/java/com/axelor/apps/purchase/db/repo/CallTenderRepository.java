package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.CallTender;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class CallTenderRepository extends JpaRepository<CallTender> {

  public CallTenderRepository() {
    super(CallTender.class);
  }

  public CallTender findByName(String name) {
    return Query.of(CallTender.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
