package l10.myorm;

import l10.DbService;
import l10.model.UserDataSet;

public class DbServiceMyOrmImpl implements DbService {

    private MyOrm myOrm;

    public DbServiceMyOrmImpl(MyOrm myOrm) {
        this.myOrm = myOrm;
    }

    @Override
    public void save(UserDataSet dataSet) {
        myOrm.save(dataSet);
    }

    @Override
    public UserDataSet read(long id) {
        return myOrm.load(id, UserDataSet.class);
    }

    @Override
    public void shutdown() {

    }
}
