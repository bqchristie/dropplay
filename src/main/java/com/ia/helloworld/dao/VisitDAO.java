package com.ia.helloworld.dao;

import org.skife.jdbi.v2.sqlobject.Bind;

/**
 * Created by brucechristie on 15-11-05.
 */
public interface VisitDAO {
    void insert(@Bind("id") int id, @Bind("name") String name);
}
