import request from "@/utils/request";

export async function getState() {
  let data = {}
  await request.get("/v1/console/state").then(res => {
    if (res.code === 200) {
      data = res.data
    }
  })
  return data
}
