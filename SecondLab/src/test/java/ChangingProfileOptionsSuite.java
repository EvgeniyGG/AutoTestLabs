import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SuiteCategories.ChangingProfileOptionTests.class)
@Suite.SuiteClasses({ProfilePageTest.class})
public class ChangingProfileOptionsSuite {
}