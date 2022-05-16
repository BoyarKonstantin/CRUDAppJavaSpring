package MVC.Blog.Konstantin.DAO;

import MVC.Blog.Konstantin.models.BlogModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlogDAO {
    private static int ID;
    private List<BlogModel> blogList;

    {
        blogList = new ArrayList<BlogModel>();

        blogList.add(new BlogModel(++ID, "TextText",
                    "Author1", "asklfnlaksjk"));
        blogList.add(new BlogModel(++ID, "TextText1",
                "Author2", "asklfnlaksjk"));
        blogList.add(new BlogModel(++ID, "TextText2",
                "Author3", "asklfnlaksjk"));
        blogList.add(new BlogModel(++ID, "TextText3",
                "Author4", "asklfnlaksjk"));

    }
    public List<BlogModel> index(){
        return blogList;
    }

    public BlogModel show(int id){
        return blogList.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(BlogModel blogModel){
        blogModel.setId(++ID);
        blogList.add(blogModel);
    }
    public void update(int id, BlogModel updateBlogModel){
        BlogModel modelToBeUpdated = show(id);
        modelToBeUpdated.setAuthor(updateBlogModel.getAuthor());
        modelToBeUpdated.setText(updateBlogModel.getText());
    }
}
