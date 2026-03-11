package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.PurchaseRequestLine;
import com.axelor.db.JpaRepository;

public class PurchaseRequestLineRepository extends JpaRepository<PurchaseRequestLine> {

  public PurchaseRequestLineRepository() {
    super(PurchaseRequestLine.class);
  }
}
