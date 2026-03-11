package com.axelor.message.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.message.db.Template;

public class TemplateRepository extends JpaRepository<Template> {

  public TemplateRepository() {
    super(Template.class);
  }

  public Template findByName(String name) {
    return Query.of(Template.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }

  // TEMPLATE ENGINE TYPE
  public static final int TEMPLATE_ENGINE_STRING_TEMPLATE = 1;
  public static final int TEMPLATE_ENGINE_GROOVY_TEMPLATE = 2;
}
