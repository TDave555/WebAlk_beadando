import { Category } from "./category.model";
import { User } from "./user.model";

export class Todo {
  id: number = 0;
  title: String="";
  description: String="";
  completed: boolean = false;
  deadLine: Date= new Date();
  owner: User = new User();
  categories: Category[]= [];

}
