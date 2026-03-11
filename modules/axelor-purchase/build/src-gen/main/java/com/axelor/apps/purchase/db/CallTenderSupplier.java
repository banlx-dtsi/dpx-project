package com.axelor.apps.purchase.db;

import com.axelor.apps.base.db.Partner;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "PURCHASE_CALL_TENDER_SUPPLIER",
  indexes = {
    @Index(
      columnList = "supplier_partner"
    ),
    @Index(
      columnList = "call_tender"
    )
  }
)
@Track(
  on = TrackEvent.UPDATE,
  fields = @TrackField(
    name = "supplierPartner"
  )
)
public class CallTenderSupplier extends AuditableModel {

  @Id
  @EntitySequence(
    name = "PURCHASE_CALL_TENDER_SUPPLIER_SEQ"
  )
  private Long id;

  @Widget(
    title = "Supplier partner"
  )
  @NotNull
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Partner supplierPartner;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private CallTender callTender;

  @Widget(
    title = "Contacts"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<Partner> contactPartnerSet;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public CallTenderSupplier() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public Partner getSupplierPartner() {
    return supplierPartner;
  }

  public void setSupplierPartner(Partner supplierPartner) {
    this.supplierPartner = supplierPartner;
  }

  public CallTender getCallTender() {
    return callTender;
  }

  public void setCallTender(CallTender callTender) {
    this.callTender = callTender;
  }

  public Set<Partner> getContactPartnerSet() {
    return contactPartnerSet;
  }

  public void setContactPartnerSet(Set<Partner> contactPartnerSet) {
    this.contactPartnerSet = contactPartnerSet;
  }

  /**
   * Add the given {@link Partner} item to the {@code contactPartnerSet} collection.
   *
   * @param item the item to add
   */
  public void addContactPartnerSetItem(Partner item) {
    if (getContactPartnerSet() == null) {
      setContactPartnerSet(new HashSet<>());
    }
    getContactPartnerSet().add(item);
  }

  /**
   * Remove the given {@link Partner} item from the {@code contactPartnerSet} collection.
   *
   * @param item the item to remove
   */
  public void removeContactPartnerSetItem(Partner item) {
    if (getContactPartnerSet() == null) {
      return;
    }
    getContactPartnerSet().remove(item);
  }

  /**
   * Clear the {@code contactPartnerSet} collection.
   */
  public void clearContactPartnerSet() {
    if (getContactPartnerSet() != null) {
      getContactPartnerSet().clear();
    }
  }

  public String getAttrs() {
    return attrs;
  }

  public void setAttrs(String attrs) {
    this.attrs = attrs;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof CallTenderSupplier)) return false;

    final CallTenderSupplier other = (CallTenderSupplier) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .omitNullValues()
      .toString();
  }
}
