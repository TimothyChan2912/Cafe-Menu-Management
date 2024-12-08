enum UserFields {
    FIRST_NAME ("First Name"),
    LAST_NAME ("Last Name"),
    EMAIL ("Email"),
    USERNAME ("Username");

    String m_name;
    UserFields(String name) {
        m_name = name;
    }

    @Override
    public String toString() {
        return m_name;
    }
}