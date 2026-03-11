package com.axelor.apps.purchase.db;

import com.axelor.apps.base.db.Company;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.axelor.message.db.Template;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "PURCHASE_CALL_TENDER",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "call_for_tender_mail_template"
    ),
    @Index(
      columnList = "company"
    )
  }
)
@Track(
  on = TrackEvent.UPDATE,
  fields = {
    @TrackField(
      name = "callTenderNeedList"
    ),
    @TrackField(
      name = "callTenderSupplierList"
    )
  }
)
public class CallTender extends AuditableModel {

  @Id
  @EntitySequence(
    name = "PURCHASE_CALL_TENDER_SEQ"
  )
  private Long id;

  @EqualsInclude
  @Widget(
    title = "Sequence"
  )
  @Column(
    unique = true
  )
  private String callTenderSeq;

  @Widget(
    title = "Call tender name"
  )
  private String name;

  @Widget(
    title = "Description"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String description;

  @Widget(
    title = "Product needs"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "callTender",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<CallTenderNeed> callTenderNeedList;

  @Widget(
    title = "Suppliers"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "callTender",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<CallTenderSupplier> callTenderSupplierList;

  @Widget(
    title = "Offers"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "callTender",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<CallTenderOffer> callTenderOfferList;

  @Widget(
    title = "Call for tender mail template"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Template callForTenderMailTemplate;

  @Widget(
    title = "Company"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Company company;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public CallTender() {
  }

  public CallTender(String name) {
    this.name = name;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getCallTenderSeq() {
    return callTenderSeq;
  }

  public void setCallTenderSeq(String callTenderSeq) {
    this.callTenderSeq = callTenderSeq;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<CallTenderNeed> getCallTenderNeedList() {
    return callTenderNeedList;
  }

  public void setCallTenderNeedList(List<CallTenderNeed> callTenderNeedList) {
    this.callTenderNeedList = callTenderNeedList;
  }

  /**
   * Add the given {@link CallTenderNeed} item to the {@code callTenderNeedList} collection.
   *
   * <p>
   * It sets {@code item.callTender = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addCallTenderNeedListItem(CallTenderNeed item) {
    if (getCallTenderNeedList() == null) {
      setCallTenderNeedList(new ArrayList<>());
    }
    getCallTenderNeedList().add(item);
    item.setCallTender(this);
  }

  /**
   * Remove the given {@link CallTenderNeed} item from the {@code callTenderNeedList} collection.
   *
   * @param item the item to remove
   */
  public void removeCallTenderNeedListItem(CallTenderNeed item) {
    if (getCallTenderNeedList() == null) {
      return;
    }
    getCallTenderNeedList().remove(item);
  }

  /**
   * Clear the {@code callTenderNeedList} collection.
   */
  public void clearCallTenderNeedList() {
    if (getCallTenderNeedList() != null) {
      getCallTenderNeedList().clear();
    }
  }

  public List<CallTenderSupplier> getCallTenderSupplierList() {
    return callTenderSupplierList;
  }

  public void setCallTenderSupplierList(List<CallTenderSupplier> callTenderSupplierList) {
    this.callTenderSupplierList = callTenderSupplierList;
  }

  /**
   * Add the given {@link CallTenderSupplier} item to the {@code callTenderSupplierList} collection.
   *
   * <p>
   * It sets {@code item.callTender = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addCallTenderSupplierListItem(CallTenderSupplier item) {
    if (getCallTenderSupplierList() == null) {
      setCallTenderSupplierList(new ArrayList<>());
    }
    getCallTenderSupplierList().add(item);
    item.setCallTender(this);
  }

  /**
   * Remove the given {@link CallTenderSupplier} item from the {@code callTenderSupplierList} collection.
   *
   * @param item the item to remove
   */
  public void removeCallTenderSupplierListItem(CallTenderSupplier item) {
    if (getCallTenderSupplierList() == null) {
      return;
    }
    getCallTenderSupplierList().remove(item);
  }

  /**
   * Clear the {@code callTenderSupplierList} collection.
   */
  public void clearCallTenderSupplierList() {
    if (getCallTenderSupplierList() != null) {
      getCallTenderSupplierList().clear();
    }
  }

  public List<CallTenderOffer> getCallTenderOfferList() {
    return callTenderOfferList;
  }

  public void setCallTenderOfferList(List<CallTenderOffer> callTenderOfferList) {
    this.callTenderOfferList = callTenderOfferList;
  }

  /**
   * Add the given {@link CallTenderOffer} item to the {@code callTenderOfferList} collection.
   *
   * <p>
   * It sets {@code item.callTender = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addCallTenderOfferListItem(CallTenderOffer item) {
    if (getCallTenderOfferList() == null) {
      setCallTenderOfferList(new ArrayList<>());
    }
    getCallTenderOfferList().add(item);
    item.setCallTender(this);
  }

  /**
   * Remove the given {@link CallTenderOffer} item from the {@code callTenderOfferList} collection.
   *
   * @param item the item to remove
   */
  public void removeCallTenderOfferListItem(CallTenderOffer item) {
    if (getCallTenderOfferList() == null) {
      return;
    }
    getCallTenderOfferList().remove(item);
  }

  /**
   * Clear the {@code callTenderOfferList} collection.
   */
  public void clearCallTenderOfferList() {
    if (getCallTenderOfferList() != null) {
      getCallTenderOfferList().clear();
    }
  }

  public Template getCallForTenderMailTemplate() {
    return callForTenderMailTemplate;
  }

  public void setCallForTenderMailTemplate(Template callForTenderMailTemplate) {
    this.callForTenderMailTemplate = callForTenderMailTemplate;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
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
    if (!(obj instanceof CallTender)) return false;

    final CallTender other = (CallTender) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getCallTenderSeq(), other.getCallTenderSeq())
      && (getCallTenderSeq() != null);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("callTenderSeq", getCallTenderSeq())
      .add("name", getName())
      .omitNullValues()
      .toString();
  }
}
