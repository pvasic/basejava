package com.javaops.web.storage;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Vasichkin Pavel
 */
@RunWith(Categories.class)
@Categories.ExcludeCategory(CategoryArray.class)
@Suite.SuiteClasses({
        ListStorageTest.class,
        MapStorageTest.class
})
public class AllCollectionStorageClassTest {
}
