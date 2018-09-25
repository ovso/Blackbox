package io.github.ovso.blackbox.utils;

public class LealmUtils {
  private LealmUtils() {}

  /*
  private void migrationRealm() {
    realmConfiguration = new RealmConfiguration.IBuilder().schemaVersion(1)
        .migration((realm, oldVersion, newVersion) -> {
          RealmSchema schema = realm.getSchema();
          if (oldVersion == 0) {
            //schema.create("").addField("name", String.class).addField("age", int.class);
            //schema.get("")
            //oldVersion++;
          }
        })
        .build();
  }

  private void deleteRealmIfMigrationNeeded() {
    Realm.setDefaultConfiguration(
        new RealmConfiguration.IBuilder().deleteRealmIfMigrationNeeded().build());
  }

  private void deleteRealm() {
    Realm.deleteRealm(new RealmConfiguration.IBuilder().deleteRealmIfMigrationNeeded().build());
  }
  */

}
