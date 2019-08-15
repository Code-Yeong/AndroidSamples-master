package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_chivan_realmdemo_object_DogRealmProxy extends com.chivan.realmdemo.object.Dog
    implements RealmObjectProxy, com_chivan_realmdemo_object_DogRealmProxyInterface {

    static final class DogColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long nameIndex;
        long ageIndex;

        DogColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Dog");
            this.nameIndex = addColumnDetails("name", "name", objectSchemaInfo);
            this.ageIndex = addColumnDetails("age", "age", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        DogColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new DogColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final DogColumnInfo src = (DogColumnInfo) rawSrc;
            final DogColumnInfo dst = (DogColumnInfo) rawDst;
            dst.nameIndex = src.nameIndex;
            dst.ageIndex = src.ageIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private DogColumnInfo columnInfo;
    private ProxyState<com.chivan.realmdemo.object.Dog> proxyState;

    com_chivan_realmdemo_object_DogRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (DogColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.chivan.realmdemo.object.Dog>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$age() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.ageIndex);
    }

    @Override
    public void realmSet$age(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.ageIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.ageIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Dog", 2, 0);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("age", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DogColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new DogColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Dog";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Dog";
    }

    @SuppressWarnings("cast")
    public static com.chivan.realmdemo.object.Dog createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.chivan.realmdemo.object.Dog obj = realm.createObjectInternal(com.chivan.realmdemo.object.Dog.class, true, excludeFields);

        final com_chivan_realmdemo_object_DogRealmProxyInterface objProxy = (com_chivan_realmdemo_object_DogRealmProxyInterface) obj;
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("age")) {
            if (json.isNull("age")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'age' to null.");
            } else {
                objProxy.realmSet$age((int) json.getInt("age"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.chivan.realmdemo.object.Dog createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.chivan.realmdemo.object.Dog obj = new com.chivan.realmdemo.object.Dog();
        final com_chivan_realmdemo_object_DogRealmProxyInterface objProxy = (com_chivan_realmdemo_object_DogRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("age")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$age((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'age' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_chivan_realmdemo_object_DogRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.chivan.realmdemo.object.Dog.class), false, Collections.<String>emptyList());
        io.realm.com_chivan_realmdemo_object_DogRealmProxy obj = new io.realm.com_chivan_realmdemo_object_DogRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.chivan.realmdemo.object.Dog copyOrUpdate(Realm realm, DogColumnInfo columnInfo, com.chivan.realmdemo.object.Dog object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.chivan.realmdemo.object.Dog) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.chivan.realmdemo.object.Dog copy(Realm realm, DogColumnInfo columnInfo, com.chivan.realmdemo.object.Dog newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.chivan.realmdemo.object.Dog) cachedRealmObject;
        }

        com_chivan_realmdemo_object_DogRealmProxyInterface realmObjectSource = (com_chivan_realmdemo_object_DogRealmProxyInterface) newObject;

        Table table = realm.getTable(com.chivan.realmdemo.object.Dog.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.nameIndex, realmObjectSource.realmGet$name());
        builder.addInteger(columnInfo.ageIndex, realmObjectSource.realmGet$age());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_chivan_realmdemo_object_DogRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.chivan.realmdemo.object.Dog object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.chivan.realmdemo.object.Dog.class);
        long tableNativePtr = table.getNativePtr();
        DogColumnInfo columnInfo = (DogColumnInfo) realm.getSchema().getColumnInfo(com.chivan.realmdemo.object.Dog.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$name = ((com_chivan_realmdemo_object_DogRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((com_chivan_realmdemo_object_DogRealmProxyInterface) object).realmGet$age(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.chivan.realmdemo.object.Dog.class);
        long tableNativePtr = table.getNativePtr();
        DogColumnInfo columnInfo = (DogColumnInfo) realm.getSchema().getColumnInfo(com.chivan.realmdemo.object.Dog.class);
        com.chivan.realmdemo.object.Dog object = null;
        while (objects.hasNext()) {
            object = (com.chivan.realmdemo.object.Dog) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$name = ((com_chivan_realmdemo_object_DogRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((com_chivan_realmdemo_object_DogRealmProxyInterface) object).realmGet$age(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.chivan.realmdemo.object.Dog object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.chivan.realmdemo.object.Dog.class);
        long tableNativePtr = table.getNativePtr();
        DogColumnInfo columnInfo = (DogColumnInfo) realm.getSchema().getColumnInfo(com.chivan.realmdemo.object.Dog.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$name = ((com_chivan_realmdemo_object_DogRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((com_chivan_realmdemo_object_DogRealmProxyInterface) object).realmGet$age(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.chivan.realmdemo.object.Dog.class);
        long tableNativePtr = table.getNativePtr();
        DogColumnInfo columnInfo = (DogColumnInfo) realm.getSchema().getColumnInfo(com.chivan.realmdemo.object.Dog.class);
        com.chivan.realmdemo.object.Dog object = null;
        while (objects.hasNext()) {
            object = (com.chivan.realmdemo.object.Dog) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$name = ((com_chivan_realmdemo_object_DogRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((com_chivan_realmdemo_object_DogRealmProxyInterface) object).realmGet$age(), false);
        }
    }

    public static com.chivan.realmdemo.object.Dog createDetachedCopy(com.chivan.realmdemo.object.Dog realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.chivan.realmdemo.object.Dog unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.chivan.realmdemo.object.Dog();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.chivan.realmdemo.object.Dog) cachedObject.object;
            }
            unmanagedObject = (com.chivan.realmdemo.object.Dog) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_chivan_realmdemo_object_DogRealmProxyInterface unmanagedCopy = (com_chivan_realmdemo_object_DogRealmProxyInterface) unmanagedObject;
        com_chivan_realmdemo_object_DogRealmProxyInterface realmSource = (com_chivan_realmdemo_object_DogRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$age(realmSource.realmGet$age());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Dog = proxy[");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{age:");
        stringBuilder.append(realmGet$age());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_chivan_realmdemo_object_DogRealmProxy aDog = (com_chivan_realmdemo_object_DogRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aDog.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDog.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aDog.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
