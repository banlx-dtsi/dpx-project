package com.axelor.studio.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.studio.db.AppPurchase;

public class AppPurchaseRepository extends JpaRepository<AppPurchase> {

  public AppPurchaseRepository() {
    super(AppPurchase.class);
  }
}
