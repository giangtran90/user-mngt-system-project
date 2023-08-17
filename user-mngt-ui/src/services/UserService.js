import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8080/api/v1/users";

class UserService {
    getAllUsers() {
        return axios.get(USER_API_BASE_URL);
    }

    saveUser(user) {
        return axios.post(USER_API_BASE_URL, user);
    }

    deleteUser(id) {
        return axios.delete(USER_API_BASE_URL + "/" + id);
    }
}
export default new UserService()