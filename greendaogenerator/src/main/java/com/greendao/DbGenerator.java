package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DbGenerator {


    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.assessment.zolostays.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema){
        addUserEntities(schema);
    }

    private static Entity addUserEntities(final Schema schema){
        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey().autoincrement();
        user.addLongProperty("user_id").notNull();
        user.addStringProperty("name").notNull();
        user.addStringProperty("email").notNull().unique();
        user.addStringProperty("phone").notNull().unique();
        user.addStringProperty("password").notNull();
        return user;
    }
}
