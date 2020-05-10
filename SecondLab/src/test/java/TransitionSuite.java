import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SuiteCategories.TransitionTests.class)
@Suite.SuiteClasses({MainPageTest.class, ProfilePageTest.class, HomePageTest.class})
public class TransitionSuite {
}
