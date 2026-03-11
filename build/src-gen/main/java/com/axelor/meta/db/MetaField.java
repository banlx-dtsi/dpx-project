package com.axelor.meta.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
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
 * This object stores the fields.
 */
@Entity
@Cacheable
@Table(
  name = "META_FIELD",
  indexes = {
    @Index(
      columnList = "meta_model"
    ),
    @Index(
      columnList = "name"
    )
  }
)
public class MetaField extends AuditableModel {

  @Id
  @EntitySequence(
    name = "META_FIELD_SEQ"
  )
  private Long id;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaModel metaModel;

  @Widget(
    title = "Name"
  )
  @NotNull
  private String name;

  @Widget(
    title = "Package"
  )
  private String packageName;

  @Widget(
    title = "Type"
  )
  @NotNull
  private String typeName;

  @Widget(
    translatable = true,
    title = "Label"
  )
  private String label;

  @Widget(
    title = "Relationship",
    selection = "relationship.field.selection"
  )
  private String relationship;

  @Widget(
    title = "Mapped by"
  )
  private String mappedBy;

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

  @Column(
    name = "is_json"
  )
  private Boolean json = Boolean.FALSE;

  public MetaField() {
  }

  public MetaField(String name) {
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

  public MetaModel getMetaModel() {
    return metaModel;
  }

  public void setMetaModel(MetaModel metaModel) {
    this.metaModel = metaModel;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getRelationship() {
    return relationship;
  }

  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }

  public String getMappedBy() {
    return mappedBy;
  }

  public void setMappedBy(String mappedBy) {
    this.mappedBy = mappedBy;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getJson() {
    return json == null ? Boolean.FALSE : json;
  }

  public void setJson(Boolean json) {
    this.json = json;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof MetaField)) return false;

    final MetaField other = (MetaField) obj;
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
      .add("name", getName())
      .add("packageName", getPackageName())
      .add("typeName", getTypeName())
      .add("label", getLabel())
      .add("relationship", getRelationship())
      .add("mappedBy", getMappedBy())
      .add("json", getJson())
      .omitNullValues()
      .toString();
  }
}
