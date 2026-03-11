package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.PurchaseOrderLineTax;
import com.axelor.db.JpaRepository;

public class PurchaseOrderLineTaxRepository extends JpaRepository<PurchaseOrderLineTax> {

  public PurchaseOrderLineTaxRepository() {
    super(PurchaseOrderLineTax.class);
  }
}
