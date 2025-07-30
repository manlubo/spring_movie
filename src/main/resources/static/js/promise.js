  // 전역변수로 counter 0
  // 로컬 처리함수, 어싱크함수
  let counter = 0;
  // function callLocal(n, cb) {
  //   const add = 10 ** n;
  //   counter += add;
  //   console.log(`[local] 10^${n} = ${add} -> counter : ${counter}`);
  //   if(cb) cb();
  // }
  // function callAsync(n, cb) {
  //   const add = 10 ** n;
  //   const delay = Math.floor(Math.random() * 1000); // 0 ~ 999
  //   setTimeout(_ => {
  //     counter += add;
  //     console.log(`[local] 10^${n} = ${add} -> counter : ${counter}, delay: ${delay}ms`);
  //     if(cb) cb();
  //   }, delay)
  // }

  function callLocal(n) {
    return new Promise((resolve) => {
      const add = 10 ** n;
      counter += add;
      console.log(`[local] 10^${n} = ${add} -> counter : ${counter}`);
      resolve();
    });

  }
  function callAsync(n) {
  return new Promise((resolve) => {
  const add = 10 ** n;
  const delay = Math.floor(Math.random() * 1000); // 0 ~ 999

  setTimeout(_ => {
  counter += add;
  console.log(`[async] 10^${n} = ${add} -> counter : ${counter}, delay: ${delay}ms`);
  resolve();
}, delay);
});
}

  // callAsync(0)
  //     .then(_ => callAsync(1))
  //     .then(_ => callAsync(2))
  //     .then(_ => callAsync(3))
  //     .then(_ => callAsync(4))
  //     .then(_ => callLocal(5))
  //     .then(_ => callLocal(6))
  //     .then(_ => callLocal(7))
  //     .then(_ => callLocal(8))
  //     .then(_ => callLocal(9))
  //     .then(_ => console.log("내가 마지막"));

  // callAsync(0, _ => {
  //   callAsync(1, _ => {
  //     callAsync(2, _ => {
  //       callAsync(3, _ => {
  //         callAsync(4, _ => {
  //           callLocal(5, _ => {
  //             callLocal(6, _ => {
  //               callLocal(7, _ => {
  //                 callLocal(8, _ => {
  //                   callLocal(9, _ => {
  //                     console.log("마지막입니다.")
  //                   })
  //                 })
  //               })
  //             })
  //           })
  //         })
  //       });
  //     });
  //   });
  // });

  // 프로세스 순서 보장

  // Promise : 미래의 값이 성공(Resolve) 혹은 실패(Reject) 될 것이라는 약속된 결과를 표현
  const promise = new Promise((resolve, reject) => {
  // if(성공) resolve(결과);
  // else reject(에러);
});

  // 상태
  // pending : 대기중
  // fulfilled : 성공 (resolve가 호출된 상태)
  // rejected : 실패 (reject가 호출된 상태)

  // promise.then(result => {/*성공 시*/})
  //     .catch(err => reject(err)/*실패 시*/)
  //     .finally(_ => {/*무조건 실행*/})
  // [].sort((a) => {});

  // function asyncTask() { // Thenable fuction - 프로미스 객체 반환 함수
  //   return new Promise(resolve => {
  //     setTimeout(() => {resolve("완료")}, 500);
  //   })
  // }



  // 장점
  // 비동기 작업 시 프로미스를 반환하는 함수를 만들어야한다.


  async function run(){
  callAsync(0);
  await callAsync(1);
  await callAsync(2);
  await callAsync(3);
  await callAsync(4);
  await callLocal(5);
  await callLocal(6);
  await callLocal(7);
  await callLocal(8);
  console.log("내가 마지막 직전");
  await callLocal(9);
  console.log("내가 마지막 !")
}

  // run();

  // function fetchWithCallback(url = "", callback) {
  //   fetch(url)
  //       .then(response => response.json())
  //       .then(data => {
  //         console.log("콜백 결과", url)
  //         console.log(data);
  //         if (callback) callback();
  //       })
  // }

  function fetchReplies(bno) {
    return fetch(`../replies/board/${bno}`)
    .then(response => response.json())
    .then(data => {
    console.log("콜백 결과", bno)
    console.log(data);
    })
  }

  // fetchWithCallback("../replies/board/1", () => fetchWithCallback("../replies/board/2", () => fetchWithCallback("../replies/board/3", _ => console.log("여전히 콜백 지옥"))));

  // fetchReplies(1)
  //     .then(_ => fetchReplies(2))
  //     .then(_ => fetchReplies(3))
  //     .then(_ => console.log("Promise Call 적용"));

  (async () => {
  await fetchReplies(1);
  await fetchReplies(2);
  await fetchReplies(3);
  console.log("IIFE + Await Call 적용")
})(); // IIFE




  // $.ajax({
  //   success: (data) => {
  //     $.ajax({
  //       success: (data) => {
  //
  //       }
  //     })
  //   }
  // })

  // $.ajax({
  //   success: (data) => {},
  //   error: (data) => {},
  //   finally: (data) => {}
  // })
  // .done((data) => {
  //
  // })
  // .fail(error => {
  //
  // })
  // .always((data) => {
  //
  // })
  // .done((data) => {
  //
  // })