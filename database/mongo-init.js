admin = db.getSiblingDB("admin");

admin.auth(process.env.MONGO_INITDB_ROOT_USERNAME,
    process.env.MONGO_INITDB_ROOT_PASSWORD);

db = db.getSiblingDB("notice");
db.createCollection("notes");
db.createCollection("tags");

admin.createUser({
    user: process.env.MONGOPREF_USER_USERNAME,
    pwd: process.env.MONGOPREF_USER_PASSWORD,
    roles: [{ role: "readWrite", db: "notice" }]
});
