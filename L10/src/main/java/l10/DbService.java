package l10;

import l10.model.UserDataSet;

public interface DbService {

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    void shutdown();

//    UserDataSet readByName(String name);
//
//    List<UserDataSet> readAll();
//
//    void shutdown();
}
