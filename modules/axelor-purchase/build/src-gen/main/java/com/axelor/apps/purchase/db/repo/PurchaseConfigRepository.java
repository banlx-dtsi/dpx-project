package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.PurchaseConfig;
import com.axelor.db.JpaRepository;

public class PurchaseConfigRepository extends JpaRepository<PurchaseConfig> {

  public PurchaseConfigRepository() {
    super(PurchaseConfig.class);
  }

  // TYPE SELECT
  public static final int PURCHASE_WT_ALWAYS = 1;
  public static final int PURCHASE_ATI_ALWAYS = 2;
  public static final int PURCHASE_WT_DEFAULT = 3;
  public static final int PURCHASE_ATI_DEFAULT = 4;
}
