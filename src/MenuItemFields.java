enum MenuItemFields {
    TITLE ("Title"),
    ITEM_ID ("Item ID"),
    DESCRIPTION ("Description"),
    PRICE ("Price");

    String m_name;
    MenuItemFields(String name) {
        m_name = name;
    }

    @Override
    public String toString() {
        return m_name;
    }
}