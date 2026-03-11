package com.axelor.meta.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.studio.db.StudioApp;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import org.hibernate.Length;

/**
 * This object stores the xml actions.
 */
@Entity
@Cacheable
@Table(
  name = "META_ACTION",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "studio_app"
    )
  }
)
public class MetaAction extends AuditableModel {

  @Id
  @EntitySequence(
    name = "META_ACTION_SEQ"
  )
  private Long id;

  private Integer priority = 0;

  @EqualsInclude
  @Widget(
    title = "Unique ID"
  )
  @Column(
    unique = true
  )
  private String xmlId;

  @NotNull
  private String name;

  @Widget(
    selection = "action.type.selection"
  )
  @NotNull
  private String type;

  @Widget(
    title = "Used as home action",
    help = "Specify whether this action can be used as home action."
  )
  private Boolean home = Boolean.FALSE;

  private String model;

  private String module;

  @Basic(
    fetch = FetchType.LAZY
  )
  @NotNull
  @Column(
    length = Length.LONG32
  )
  private String xml;

  @Widget(
    title = "Sequence"
  )
  private Integer sequence = 0;

  @Widget(
    title = "App"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private StudioApp studioApp;

  @Widget(
    title = "Custom"
  )
  private Boolean isCustom = Boolean.FALSE;

  public MetaAction() {
  }

  public MetaAction(String name) {
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

  public Integer getPriority() {
    return priority == null ? 0 : priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public String getXmlId() {
    return xmlId;
  }

  public void setXmlId(String xmlId) {
    this.xmlId = xmlId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  /**
   * Specify whether this action can be used as home action.
   *
   * @return the property value
   */
  public Boolean getHome() {
    return home == null ? Boolean.FALSE : home;
  }

  public void setHome(Boolean home) {
    this.home = home;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  public String getXml() {
    return xml;
  }

  public void setXml(String xml) {
    this.xml = xml;
  }

  public Integer getSequence() {
    return sequence == null ? 0 : sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  public StudioApp getStudioApp() {
    return studioApp;
  }

  public void setStudioApp(StudioApp studioApp) {
    this.studioApp = studioApp;
  }

  public Boolean getIsCustom() {
    return isCustom == null ? Boolean.FALSE : isCustom;
  }

  public void setIsCustom(Boolean isCustom) {
    this.isCustom = isCustom;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof MetaAction)) return false;

    final MetaAction other = (MetaAction) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getXmlId(), other.getXmlId())
      && (getXmlId() != null);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("priority", getPriority())
      .add("xmlId", getXmlId())
      .add("name", getName())
      .add("type", getType())
      .add("home", getHome())
      .add("model", getModel())
      .add("module", getModule())
      .add("sequence", getSequence())
      .add("isCustom", getIsCustom())
      .omitNullValues()
      .toString();
  }
}
