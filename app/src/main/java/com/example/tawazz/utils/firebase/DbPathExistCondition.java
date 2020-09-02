//package com.example.tawazz.utils.firebase;
//
//import com.example.tawazz.consts.Constants;
//import com.example.tawazz.database.DocHandler;
//import com.example.tawazz.database.ReadableDatabase;
//import com.example.tawazz.utils.Predicate;
//import com.example.tawazz.utils.condition.Condition;
//
//import java.util.Map;
//import java.util.UUID;
//
//public class DbPathExistCondition implements Predicate<String> {
//
//    private final ReadableDatabase readableDatabase;
//
//    public DbPathExistCondition(ReadableDatabase readableDatabase) {
//
//        this.readableDatabase = readableDatabase;
//    }
//
//    @Override
//    public boolean apply(String input) {
//        return readableDatabase.handleAllThatEqualTo(Constants.ROOMS_DATABASE_KEY, Constants.ROOM_ID_READABLE_DATABASE_KEY, input, new DocHandler() {
//            @Override
//            public void handle(Map o) {
//                UUID userId = UUID.fromString((String)map.get(Constants.USER_ID_READABLE_DATABASE_KEY));
//            }
//        });
//    }
//}
