import food from "../../public/1.png";
import free from "../../public/2.png";
import shopping from "../../public/6.png";
import education from "../../public/7.png";
import dessert from "../../public/3.png";
import stuff from "../../public/4.png";
import living from "../../public/5.png";
import unClassified from "../../public/0.png";

const category = [
  unClassified,
  food,
  free,
  dessert,
  stuff,
  living,
  shopping,
  education
];

export function lists(id) {
  return category[id];
}
