import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SuiteCategories.AuthorizationTests.class)
@Suite.SuiteClasses({MainPageTest.class})
public class AuthorizationSuite {
}