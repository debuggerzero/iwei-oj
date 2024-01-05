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

export const percentageCal = (a, b) => {
  let result = (a / (b === 0 ? 1 : b)) * 100;
  return Math.floor(result * 100) / 100;
};

export const colors = [
  { color: "#909399", percentage: 20 },
  { color: "#F56C6C", percentage: 40 },
  { color: "#E6A23C", percentage: 60 },
  { color: "#1989FA", percentage: 80 },
  { color: "#67C23A", percentage: 100 },
];
