package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.CallTenderSupplier;
import com.axelor.db.JpaRepository;

public class CallTenderSupplierRepository extends JpaRepository<CallTenderSupplier> {

  public CallTenderSupplierRepository() {
    super(CallTenderSupplier.class);
  }
}
