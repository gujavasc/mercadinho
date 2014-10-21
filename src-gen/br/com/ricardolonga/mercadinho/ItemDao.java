package br.com.ricardolonga.mercadinho;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import br.com.ricardolonga.mercadinho.Item;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table ITEM.
*/
public class ItemDao extends AbstractDao<Item, Long> {

    public static final String TABLENAME = "ITEM";

    /**
     * Properties of entity Item.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nome = new Property(1, String.class, "nome", false, "NOME");
        public final static Property Quantidade = new Property(2, Integer.class, "quantidade", false, "QUANTIDADE");
        public final static Property ValorUnitario = new Property(3, Double.class, "valorUnitario", false, "VALOR_UNITARIO");
        public final static Property ValorTotal = new Property(4, Double.class, "valorTotal", false, "VALOR_TOTAL");
        public final static Property IdCategoria = new Property(5, long.class, "idCategoria", false, "ID_CATEGORIA");
    };

    private DaoSession daoSession;

    private Query<Item> categoria_ItensQuery;

    public ItemDao(DaoConfig config) {
        super(config);
    }
    
    public ItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ITEM' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'NOME' TEXT NOT NULL ," + // 1: nome
                "'QUANTIDADE' INTEGER," + // 2: quantidade
                "'VALOR_UNITARIO' REAL," + // 3: valorUnitario
                "'VALOR_TOTAL' REAL," + // 4: valorTotal
                "'ID_CATEGORIA' INTEGER NOT NULL );"); // 5: idCategoria
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ITEM'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Item entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNome());
 
        Integer quantidade = entity.getQuantidade();
        if (quantidade != null) {
            stmt.bindLong(3, quantidade);
        }
 
        Double valorUnitario = entity.getValorUnitario();
        if (valorUnitario != null) {
            stmt.bindDouble(4, valorUnitario);
        }
 
        Double valorTotal = entity.getValorTotal();
        if (valorTotal != null) {
            stmt.bindDouble(5, valorTotal);
        }
        stmt.bindLong(6, entity.getIdCategoria());
    }

    @Override
    protected void attachEntity(Item entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Item readEntity(Cursor cursor, int offset) {
        Item entity = new Item( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // nome
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // quantidade
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3), // valorUnitario
            cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4), // valorTotal
            cursor.getLong(offset + 5) // idCategoria
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Item entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNome(cursor.getString(offset + 1));
        entity.setQuantidade(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setValorUnitario(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
        entity.setValorTotal(cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4));
        entity.setIdCategoria(cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Item entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Item entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "itens" to-many relationship of Categoria. */
    public List<Item> _queryCategoria_Itens(long idCategoria) {
        synchronized (this) {
            if (categoria_ItensQuery == null) {
                QueryBuilder<Item> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.IdCategoria.eq(null));
                categoria_ItensQuery = queryBuilder.build();
            }
        }
        Query<Item> query = categoria_ItensQuery.forCurrentThread();
        query.setParameter(0, idCategoria);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getCategoriaDao().getAllColumns());
            builder.append(" FROM ITEM T");
            builder.append(" LEFT JOIN CATEGORIA T0 ON T.'ID_CATEGORIA'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Item loadCurrentDeep(Cursor cursor, boolean lock) {
        Item entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Categoria categoria = loadCurrentOther(daoSession.getCategoriaDao(), cursor, offset);
         if(categoria != null) {
            entity.setCategoria(categoria);
        }

        return entity;    
    }

    public Item loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Item> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Item> list = new ArrayList<Item>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Item> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Item> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}