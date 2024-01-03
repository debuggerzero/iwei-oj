export const randomTagTypeList = (list) => {
  const types = ["", "success", "danger", "warning"];
  const newList = [];
  for (let i = 0; i < list.length; i++) {
    let randomIndex = Math.floor(Math.random() * types.length);
    newList.push({
      id: list[i].id,
      name: list[i].name,
      type: types[randomIndex],
    });
  }
  return newList;
};

export const formatDate = (date) => {
  if (date === undefined) {
    return null;
  }
  date = new Date(date);
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, "0");
  const day = date.getDate().toString().padStart(2, "0");
  return `${year}-${month}-${day}`;
};
