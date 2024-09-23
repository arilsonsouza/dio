// funções
function addNumber(x: number, y: number): number {
  return y + y;
}

function callToPhone(phone: number | string): number | string {
  return phone;
}

async function getDatabase(id: number): Promise<string> {
  return "José";
}

let soma: number = addNumber(4, 7);
console.log(soma);
