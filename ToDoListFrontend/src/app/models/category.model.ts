import { Todo } from "./todo.model";
import { User } from "./user.model";

export class Category {
  id: number=0;
  title: String="";
  owner:User=new User();
  todosIncategory?:Todo[]
}
