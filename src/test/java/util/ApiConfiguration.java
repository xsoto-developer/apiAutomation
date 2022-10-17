package util;

public class ApiConfiguration {

    public static final String NEW = "newItemFactory";
    public static final String UPDATE = "updateItemFactory";
    public static final String CONTENT = "Content";

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String PUT = "put";
    public static final String DELETE = "delete";

    public static final String ESSENTIAL_PATH = "/api/items.json";
    public static final String SPECIFIC_PATH = "/api/items/%s.json";

    public static final String CREATE_ITEM=GetProperties.getInstance().getHost().concat(ESSENTIAL_PATH);
    public static final String UPDATE_ITEM=GetProperties.getInstance().getHost().concat(SPECIFIC_PATH);
    public static final String READ_ITEM=GetProperties.getInstance().getHost().concat(SPECIFIC_PATH);
    public static final String DELETE_ITEM=GetProperties.getInstance().getHost().concat(SPECIFIC_PATH);

}
