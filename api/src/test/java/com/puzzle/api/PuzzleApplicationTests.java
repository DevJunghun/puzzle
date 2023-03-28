package com.puzzle.api;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class PuzzleApplicationTests extends BaseTest{

    @TestFactory
    DynamicNode a() {
        return single("test", () -> {});
    }
//    @TestFactory
//    DynamicNode contextLoads() {
//        return single("test", () -> {});
//    }
//
//    @TestFactory
//    DynamicNode contextLoads1() {
//        return single("test", () -> {});
//    }
//
//    @TestFactory
//    DynamicNode contextLoads2() {
//        return single("test", () -> {});
//    }
//
//    @TestFactory
//    DynamicNode contextLoads3() {
//        return single("test", () -> {});
//    }
//
//    @TestFactory
//    DynamicNode contextLoads4() {
//        return single("test", () -> {});
//    }

}
