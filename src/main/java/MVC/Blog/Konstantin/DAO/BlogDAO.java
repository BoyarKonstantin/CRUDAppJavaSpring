package MVC.Blog.Konstantin.DAO;

import MVC.Blog.Konstantin.models.BlogModel;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BlogDAO {
    private static int ID;

    private static final String URL = "jdbc:postgresql://localhost:5432/Blog";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Dod900ls";


    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<BlogModel> index() {
        List<BlogModel> blogList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM blog"; // Обращаемся к Postgres таблице
            statement.executeQuery(SQL);
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                BlogModel blogModel = new BlogModel();

                blogModel.setId(resultSet.getInt("id"));
                blogModel.setAuthor(resultSet.getString("author"));

                blogModel.setTitle(resultSet.getString("title"));
                blogModel.setText(resultSet.getString("text"));

                blogList.add(blogModel);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blogList;
    }

    public BlogModel show(int id) {
        BlogModel blogModel = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM blog where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            blogModel = new BlogModel();

            blogModel.setId(resultSet.getInt("id"));
            blogModel.setText(resultSet.getString("text"));

            blogModel.setAuthor(resultSet.getString("author"));
            blogModel.setTitle(resultSet.getString("title"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blogModel;
    }

    public void save(BlogModel blogModel) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO blog VALUES(1, ?, ?, ?)");

            preparedStatement.setString(1, blogModel.getAuthor());
            preparedStatement.setString(2, blogModel.getTitle());
            preparedStatement.setString(3, blogModel.getText());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, BlogModel updateBlogModel) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE blog SET author = ?, title = ?, text = ? WHERE id=?");
            preparedStatement.setInt(4, updateBlogModel.getId());
            preparedStatement.setString(2, updateBlogModel.getTitle());
            preparedStatement.setString(1, updateBlogModel.getAuthor());
            preparedStatement.setString(3, updateBlogModel.getText());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM blog WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
