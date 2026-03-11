package com.axelor.message.db;

import com.axelor.apps.base.db.Partner;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.VirtualColumn;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(
  name = "MESSAGE_EMAIL_ADDRESS",
  indexes = {
    @Index(
      columnList = "address"
    ),
    @Index(
      columnList = "name"
    )
  }
)
public class EmailAddress extends AuditableModel {

  @Id
  @EntitySequence(
    name = "MESSAGE_EMAIL_ADDRESS_SEQ"
  )
  private Long id;

  @Widget(
    title = "Address"
  )
  private String address;

  @Widget(
    title = "Contact"
  )
  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "emailAddress",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Partner partner;

  @Widget(
    readonly = true
  )
  @NameColumn
  @VirtualColumn
  @Access(AccessType.PROPERTY)
  @NotNull
  private String name;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public EmailAddress() {
  }

  public EmailAddress(String address) {
    this.address = address;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Partner getPartner() {
    return partner;
  }

  public void setPartner(Partner partner) {
    if (getPartner() != null) {
      getPartner().setEmailAddress(null);
    }
    if (partner != null) {
      partner.setEmailAddress(this);
    }
    this.partner = partner;
  }

  public String getName() {
    try {
      name = computeName();
    } catch (NullPointerException e) {
      Logger logger = LoggerFactory.getLogger(getClass());
      logger.error("NPE in function field: getName()", e);
    }
    return name;
  }

  protected String computeName() {
    String name = "";
    	    if(partner != null && partner.getFullName() != null)  {   name += partner.getFullName()+" ";  }
    	    if(address != null)  {   name += "["+address+"]";  }
    	    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    if (!(obj instanceof EmailAddress)) return false;

    final EmailAddress other = (EmailAddress) obj;
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
      .add("address", getAddress())
      .omitNullValues()
      .toString();
  }
}
